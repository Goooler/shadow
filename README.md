# Gradle Shadow

Gradle plugin for creating fat/uber JARs with support for package relocation.

## Documentation

Read the [User Guide](https://imperceptiblethoughts.com/shadow/)!

## Current Status

[![Maven Central](https://img.shields.io/maven-central/v/io.github.goooler.shadow/shadow-gradle-plugin)](https://central.sonatype.com/artifact/io.github.goooler.shadow/shadow-gradle-plugin)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.goooler.shadow/shadow-gradle-plugin?&server=https://s01.oss.sonatype.org/)](https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/goooler/shadow/shadow-gradle-plugin)
[![Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/io.github.goooler.shadow)](https://plugins.gradle.org/plugin/io.github.goooler.shadow)
[![CI](https://github.com/Goooler/shadow/actions/workflows/main.yml/badge.svg?branch=trunk&event=push)](https://github.com/Goooler/shadow/actions/workflows/main.yml?query=branch:trunk+event:push)
[![License](https://img.shields.io/github/license/goooler/shadow.svg)](LICENSE)

## Latest Test Compatibility

| Gradle Version | Shadow Version |
|----------------|----------------|
| 5.x            | 5.2.0 - 6.0.0  |
| 6.x            | 5.2.0 - 6.1.0  |
| 7.x            | 7.0.0+         |
| 8.x            | 8.0.0+         |

**NOTE**: Shadow v5.+ is compatible with Gradle 5.x - 6.x and Java 7 - 15 _only_, v6.1.0+ requires Java 8+.
