package com.github.jengelman.gradle.plugins.shadow

import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.SelfResolvingDependency
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.util.GradleVersion

class ShadowExtension {
    private final transient Project project

    ShadowExtension(Project project) {
        this.project = project
    }

    void component(MavenPublication publication) {

        if (GradleVersion.current() >= GradleVersion.version("6.6")) {
            publication.artifact(project.tasks.named("shadowJar"))
        } else {
            publication.artifact(project.tasks.shadowJar)
        }

        final def allDependencies = project.provider {
            project.configurations.shadow.allDependencies.collect {
                if ((it instanceof ProjectDependency) || !(it instanceof SelfResolvingDependency)) {
                    new Dep(it.group, it.name, it.version)
                }
            }
        }
        publication.pom { MavenPom pom ->
            pom.withXml { xml ->
                def dependenciesNode = xml.asNode().get('dependencies') ?: xml.asNode().appendNode('dependencies')
                allDependencies.get().each {
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
