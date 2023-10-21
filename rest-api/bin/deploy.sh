cd ..
mvn clean compile dependency:copy-dependencies -DincludeScope=runtime
docker build --platform linux/amd64 -t docker-image:test .
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 452385826394.dkr.ecr.us-east-1.amazonaws.com
docker tag docker-image:test 452385826394.dkr.ecr.us-east-1.amazonaws.com/hello-world:latest
docker push 452385826394.dkr.ecr.us-east-1.amazonaws.com/hello-world:latest
aws lambda delete-function --function-name=hello-world
aws lambda create-function --function-name hello-world --package-type Image --code ImageUri=452385826394.dkr.ecr.us-east-1.amazonaws.com/hello-world:latest --role arn:aws:iam::452385826394:role/lambda-ex --timeout 120 --memory-size 256