plugins {
	java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.30"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("com.apollographql.federation:federation-graphql-java-support:5.4.0")
    implementation("com.graphql-java:graphql-java-extended-scalars:22.0")
    implementation("org.projectlombok:lombok:$lombokVersion")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
