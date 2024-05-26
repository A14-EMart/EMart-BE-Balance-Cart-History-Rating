plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	jacoco
}

group = "com.a14.emart"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}
var jsonwebtokenVersion = "0.11.5"
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")
//	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2:2.2.222")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
	implementation("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
	implementation("io.jsonwebtoken:jjwt-jackson:$jsonwebtokenVersion")
	implementation("org.springframework.security:spring-security-core")
	implementation("org.springframework.security:spring-security-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("io.github.cdimascio:dotenv-java:2.2.3")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport) //to always generate report after tests
}

tasks.jacocoTestReport {
	classDirectories.setFrom(files(classDirectories.files.map{
		fileTree(it) {exclude("**/*Application**")}
	}))
	dependsOn(tasks.test)
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}
