
var inicio = angular.module("mainMod", []);
inicio.constant("baseUrl", "http://localhost:8080/Vinoteca2Angular/webresources/paraAngular");
inicio.controller("inicSesCtrl", function($scope, $http, baseUrl) { // Inyectamos recursos
    $scope.displayMode = "list"; // Variable que controla la vista
    $scope.inicSesionAbonado = function(id) {
        $http({
            method: "GET",
            url: baseUrl + "/abonado/" + id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.abonado = response.data; //?
            $scope.displayMode = "AbVerTablaPreferencias"; //cambiar vista
            console.log("exito: " + response.status);
            console.log("cuerpo: " + response.data);
            
        },function(response) { //casca, mostrar error
            console.log("error: " + response.status);
        });
    };
    
    $scope.inicSesionEmpleado = function(id) {
        $http({
            method: "GET",
            url: baseUrl + "/empleado/" + id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.empleado = response.data; //?
            $scope.displayMode = "EmplVerTablaPedidos"; //cambiar vista
            console.log("exito: " + response.status);
            console.log("cuerpo: " + response.data);
            
        },function(response) { //casca, mostrar error
            console.log("error: " + response.status);
        });
    };

});
           