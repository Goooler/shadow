package com.github.jengelman.gradle.plugins.shadow

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.SelfResolvingDependency
import org.gradle.api.provider.Provider
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication

class ShadowExtension {
    private final def archiveFile
    private final Provider<List<Dep>> allDependencies

    ShadowExtension(Project project) {
        archiveFile = project.provider { project.tasks.withType(ShadowJar).getByName("shadowJar").archiveFile }
        allDependencies = project.provider {
            project.configurations.getByName("shadow").allDependencies.findAll {
                it instanceof ProjectDependency || it instanceof SelfResolvingDependency
            }.collect {
                new Dep(it.group, it.name, it.version)
            }
        }
    }

    void component(MavenPublication publication) {
        publication.artifact(archiveFile.get())

        final def allDeps = allDependencies.get()
        publication.pom { MavenPom pom ->
            pom.withXml { xml ->
                def dependenciesNode = xml.asNode().get('dependencies') ?: xml.asNode().appendNode('dependencies')
                allDeps.each {
                    def dependencyNode = dependenciesNode.appendNode('dependency')
                    dependencyNode.appendNode('groupId', it.group)
                    dependencyNode.appendNode('artifactId', it.name)
                    dependencyNode.appendNode('version', it.version)
                    dependencyNode.appendNode('scope', 'runtime')
                }
            }
        }
    }

    private class Dep {
        String group
        String name
        String version

        Dep(String group, String name, String version) {
            this.group = group
            this.name = name
            this.version = version
        }
    }
}
