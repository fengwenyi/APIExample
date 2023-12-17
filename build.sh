gradle bootjar
docker build -t fengwenyi/erwin-service:0.0.1 .
docker run -d -e active=prod -p 9090:9090 --name erwin-service fengwenyi/erwin-service:0.0.1
docker logs -f erwin-service
docker stop erwin-service


docker ps
docker stop [id]
docker rm [id]