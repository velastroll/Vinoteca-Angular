
var inicio = angular.module("mainMod", []);
inicio.constant("baseUrl", "http://localhost:8080/Vinoteca2Angular/webresources/paraAngular");
inicio.controller("getUserCtrl", function($scope, $http, baseUrl) { // Inyectamos recursos
    $scope.displayMode = "login"; // Variable que controla la vista
    $scope.getAbonado = function(id) {
        $http({
            method: "GET",
            url: baseUrl + "/abonado/" + id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.abonado = response.data; //?
            $scope.displayMode = "preferences"; //cambiar vista
            console.log("exito: " + response.statusText);
            console.log("cuerpo: " + response.data);
            
            // Obtiene los vinos disponibles
            $scope.getVinosDisponibles(id);
            
        },function(response) { //casca, mostrar error
            $scope.inicioSesion = response.statusText;
            console.log("error: " + response.statusText);
        });
    };
    
    $scope.getEmpleado = function(id) {
        $http({
            method: "GET",
            url: baseUrl + "/empleado/" + id
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
    
    $scope.getVinosDisponibles = function(id){
        $http({ // peticion get
            method: "GET",
            url: baseUrl + "/preferencias/vinos/" + id
        }).then( // peticion correcta
                function(response){
                    $scope.vinillos = response.data; // Vino[] 
                    console.log("exito: " + response.statusText);
                    console.log("cuerpo: " + response.data);    
                }, // peticion incorrecta
                function(response){
                    $scope.vinosStatus = response.statusText;
                    console.log("error: " + response.statusText);
                });
    };
});
           