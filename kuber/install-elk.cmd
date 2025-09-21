@echo off
chcp 65001 > nul
echo ========================================
echo    INSTALADOR RÁPIDO ELK PARA KIND
echo ========================================
echo.

echo [1/5] Verificando Kubernetes...
kubectl cluster-info
if %errorlevel% neq 0 (
    echo ERROR: Kubernetes no está funcionando
    echo Ejecuta: kind create cluster
    pause
    exit /b 1
)

echo [2/5] Creando namespace logging...
kubectl create namespace logging --dry-run=client -o yaml | kubectl apply -f -

echo [3/5] Instalando Elasticsearch...
helm repo add elastic https://helm.elastic.co > nul 2>&1
helm repo update > nul
helm upgrade --install elasticsearch elastic/elasticsearch ^
  -n logging ^
  --set replicas=1 ^
  --set persistence.enabled=false ^
  --set resources.requests.memory=512Mi ^
  --set resources.requests.cpu=250m ^
  --set minimumMasterNodes=1 ^
  --wait --timeout=300s

echo [4/5] Instalando Kibana...
helm upgrade --install kibana elastic/kibana ^
  -n logging ^
  --set service.type=NodePort ^
  --set resources.requests.memory=256Mi ^
  --set resources.requests.cpu=100m ^
  --set waitForElasticsearch=false ^
  --wait --timeout=180s

echo [5/5] Instalando Logstash...
helm upgrade --install logstash elastic/logstash ^
  -n logging ^
  --set resources.requests.memory=256Mi ^
  --set resources.requests.cpu=100m ^
  --wait --timeout=180s

echo.
echo ========================================
echo         INSTALACIÓN COMPLETADA
echo ========================================
echo.
echo Comandos útiles:
echo   Ver pods: kubectl get pods -n logging -w
echo   Ver servicios: kubectl get svc -n logging
echo   Acceder Kibana: kubectl port-forward -n logging svc/kibana-kibana 5601:5601
echo   Acceder Elastic: kubectl port-forward -n logging svc/elasticsearch-master 9200:9200
echo.
echo Para obtener la contraseña de Elasticsearch:
echo   kubectl get secrets -n logging elasticsearch-master-credentials -ojsonpath={.data.password} | base64 -d
echo.
pause