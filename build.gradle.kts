plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.7"
    id("org.springframework.boot") version "4.1.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Миграции
    implementation("org.flywaydb:flyway-core:13.13.0")

    // Работа с JWT
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")

    // Валидация
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")


    // Драйвер PostgreSQL
    implementation("org.postgresql:postgresql:42.7.15")


    // Spring-boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.2.1")

    // Tests
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}