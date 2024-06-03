# Java 17을 기반으로 하는 OpenJDK 이미지 사용
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션의 jar 파일과 application.yml, .env 설정 파일 복사
COPY build/libs/*.jar app.jar
COPY src/main/resources/application.yml application.yml
COPY src/main/resources/application-server.yml application-server.yml

# 애플리케이션 실행 포트
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=server"]
