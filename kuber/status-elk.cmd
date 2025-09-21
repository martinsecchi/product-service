@echo off
chcp 65001 > nul
echo ========================================
echo       ESTADO ACTUAL DEL CLUSTER ELK
echo ========================================
echo.

echo Namespaces:
kubectl get namespaces | findstr "logging"
echo.

echo Pods en logging:
kubectl get pods -n logging
echo.

echo Services en logging:
kubectl get svc -n logging
echo.

echo Helm releases en logging:
helm list -n logging
echo.

echo Events recientes:
kubectl get events -n logging --sort-by=.lastTimestamp | tail -5
echo.

echo ========================================
echo        COMANDOS RÁPIDOS DE ACCESO
echo ========================================
echo Para forward de puertos:
echo   Kibana:     kubectl port-forward -n logging svc/kibana-kibana 5601:5601
echo   Elastic:    kubectl port-forward -n logging svc/elasticsearch-master 9200:9200
echo.
pause