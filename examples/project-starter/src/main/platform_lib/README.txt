If you want override the default langs.properties, cluster.xml,
langs.properties or any other config for the Vert.x platform (i.e.
not for the module!) then you can add them in here and
they will be added to the platform classpath when running
your module using Gradle.

/**
 * Derived from
 * https://github.com/vert-x/vertx-gradle-template
 */