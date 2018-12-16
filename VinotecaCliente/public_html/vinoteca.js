
var inicio = angular.module("mainMod", []);
inicio.constant("baseUrl", "http://localhost:8080/Vinoteca2Angular/webresources/paraAngular");
inicio.controller("getUserCtrl", function($scope, $http, baseUrl) { // Inyectamos recursos
    $scope.displayMode = "login"; // Variable que controla la vista
    $scope.cesta = {
        size: 0,
        productos: {},
        add : function(item){
            console.log("Vino: " + item.id);
            if ($scope.cesta.size === 0){
                $scope.cesta.productos = [item]; // array de vinos
            } else {
            $scope.cesta.productos.push(item); // añade un vino al array
            }
            $scope.cesta.size++;
        }
    };
    
    // TODO: Recuperar los productos de la carta EJB
    $scope.getAbonado = function(id) {
        $http({
            method: "GET",
            url: baseUrl + "/abonado/" + id
        }).then(function(response) { //furrula, ir a pagina abonado
            $scope.id = id;
            $scope.abonado = response.data; //?
            $scope.displayMode = "preferences"; //cambiar vista
            console.log("exito: " + response.statusText);
            console.log("cuerpo: " + $scope.abonado[0].ab_login);
            
            // Obtiene los vinos disponibles
            $scope.getVinosDisponibles(id);
            
        },function(response) { //casca, mostrar error
            $scope.inicioSesion = response.statusText;
            console.log("error: " + response.statusText);
        });
    };
    
    $scope.getEmpleado = function(empleadoid) {
        $http({
            method: "GET",
            url: baseUrl + "/empleado/" + empleadoid
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
    
    /**
     * Funcion que añade al modelo 'vinillos' la lista de vinos disponibles
     * segun las preferencias de un determinado usuario.
     * @param {type} id identificador del usuario = login
     */
    $scope.getVinosDisponibles = function(vinoid){
        $http({ // peticion get
            method: "GET",
            url: baseUrl + "/preferencias/vinos/" + vinoid
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
    
    /**
     * Funcion que añade un nuevo vino a la carta, tanto al modelo $scope.cesta 
     * como al EJB de sesión [EJB TODAVIA SIN IMPLEMENTAR]
     * @param {type} vino
     */
    $scope.add2Cart = function(vino){
        $http({
            // lo añadimos al EJB de sesion
            method: "POST",
            url: baseUrl + "/abonado/" + $scope.id + "/vinos/" + vino.id
        }).then(  // success
                function(){  
                    console.log("Abonado: " + $scope.id + "/ Vino: " + vino.id ),
                    // añadimos el vino al modelo local
                    $scope.cesta.add(vino);  
                    $scope.displayMode = "preferences"; //cambiar vista  
                }, // error
                function(response){
                    $scope.add2cartStatus = response.statusText;
                    console.log("error: " + response.statusText);
                });
    };
    
    
    /**
     * Function para cambiar de vista
     * @param {type} url
     */
    $scope.changeView = function(url){
        $scope.displayMode = url;
        $scope.buyStatus = '';
        $scope.add2cartStatus = '';
    };
    
    /**
     * Funcion que compra todo lo que hay en la cesta
     */
    $scope.buy = function(){
        for (item in $scope.cesta.productos){
            $scope.buyWine(item);
        }
    };
    
    $scope.buyWine = function(wine){
        $http({
            method: "GET",
            url: baseUrl + "/" + $scope.id + "/" + wine.id
        }).then(function(response){
            $scope.buyStatus = response.statusText;
            index = $scope.cesta.productos.indexOf(wine);
            $scope.cesta.productos.splice(index, 1);
            $scope.cesta.size--;
        }, function(response){
            $scope.buyStatus = response.statusText;
            console.log("ERROR[buy(" + wine.id + ")]: " + response.statusText);
        });
    };
});
           