# tra-spring-boot-registration

* Java 8
* Spring Boot 
    * Spring Data & Hibernate (spring-boot-starter-data-jpa)
    * Thymeleaf (spring-boot-starter-thymeleaf)
    * Spring Security (spring-boot-starter-security)
* H2 DB
* Maven

__SERVER PORT : 8080__


You can build & run docker image with commands

$ docker build -t <IMAGE_NAME> . (eg. $ docker build -t conference .)

$ docker run -p <PORT>:8080 <IMAGE_NAME> (eg. $ docker run -p 8090:8080 conference)

You can also pull it from public repo with the command

$ docker pull gottarollwithit/conference

You can also see an demo application running on docker in my VPS: **"http://80.211.147.168:8086"**


--------

The exemplary output was different from what you tell in question
Network sessions should start before 17:00, but in the examples it is starting at 17:00


I used a perfect sum algorithm to find possible gapless schedule between 9:00-12:00 and 13:00-16:00
Between 16:00 and 17:00 if there are any unused talks they are scheduled manually.
After that if there is remaining time, network sessions are placed.

I suggest you to insert demo data first, the schedule page shuffles list every time. So the result is different when you refresh the page.
