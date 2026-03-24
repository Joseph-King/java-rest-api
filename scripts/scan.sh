cd ../
docker build -t test-rest-api .
trivy image test-rest-api