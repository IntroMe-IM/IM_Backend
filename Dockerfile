# Java 17을 기반으로 하는 OpenJDK 이미지 사용
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션의 jar 파일과 application.yml 설정 파일 복사
COPY build/libs/*.jar app.jar
COPY src/main/resources/application.yml application.yml

# 애플리케이션 실행 포트
EXPOSE 8080

# 환경 변수 설정
ENV DB_DRIVER=com.mysql.cj.jdbc.Driver \
    DB_URL=jdbc:mysql://db:3306/introme_dev?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8 \
    DB_USERNAME=inlee \
    DB_PASSWORD=2580 \
    JWT_SECRET=introme_jwt_secret

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
