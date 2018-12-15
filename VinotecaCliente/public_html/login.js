
var inicio = angular.module("mainMod", []);
inicio.constant("baseUrl", "http://localhost:8080/Vinoteca2Angular/webresources/paraAngular");
inicio.controller("getUserCtrl", function($scope, $http, baseUrl) { // Inyectamos recursos
    $scope.displayMode = "login"; // Variable que controla la vista
    $scope.getAbonado = function() {
        $http({
            method: "GET",
            url: baseUrl + "/abonado/" + $scope.id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.abonado = response.data; //?
            $scope.displayMode = "preferences"; //cambiar vista
            console.log("exito: " + response.statusText);
            console.log("cuerpo: " + response.data);
            
        },function(response) { //casca, mostrar error
            $scope.inicioSesion = response.statusText;
            console.log("error: " + response.statusText);
        });
    };
    
    $scope.getEmpleado = function() {
        $http({
            method: "GET",
            url: baseUrl + "/empleado/" + $scope.id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.empleado = response.data; //?
            $scope.displayMode = "orders"; //cambiar vista
            console.log("exito: " + response.statusText);
            console.log("cuerpo: " + response.data);
        },function(response) { //casca, mostrar error
            $scope.inicioSesion = response.statusText;
            console.log("error: " + response.statusText);
        });
    };

});
           