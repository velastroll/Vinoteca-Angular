
var inicio = angular.module("mainMod", []);
inicio.constant("baseUrl", "http://localhost:8080/Vinoteca2Angular/webresources/paraAngular");
inicio.controller("getUserCtrl", function($scope, $http, baseUrl) { // Inyectamos recursos
    $scope.displayMode = "login"; // Variable que controla la vista
    $scope.cesta = {
        size: 0,
        productos: [],
        add : function(item){
            console.log("Vino: " + item.id +" anadido a la cesta");
            if ($scope.cesta.size === 0){
                $scope.cesta.productos = [item]; // array de vinos
            } else {
            $scope.cesta.productos.push(item); // añade un vino al array
            };
            $scope.cesta.size++;
        },
        delete: function(item){
            console.log("Vino: " + item.id +" eliminado de cesta");
            $scope.cesta.productos.splice($scope.cesta.productos.findIndex( producto => producto === item ), 1)
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
            
            // Obtenemos los opedidos pendientes:
            $scope.getPedidosPendientes();
        },function(response) { //casca, mostrar error
            $scope.inicioSesion = response.statusText;
            console.log("error: " + response.statusText);
        });
    };
    
    /**
     * Funcion que añade al modelo 'vinillos' la lista de vinos disponibles
     * segun las preferencias de un determinado usuario.
     * @param {type} vinoid identificador del usuario = login
     */
    $scope.getVinosDisponibles = function(id){
        $http({ // peticion get
            method: "GET",
            url: baseUrl + "/" + id + "/vinos"
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
     * Funcion que añade un nuevo vino al modelo $scope.cesta 
     * @param {type} vino
     */
    $scope.add2Cart = function(vino){
        
        // añadimos el vino al modelo local
        $scope.cesta.add(vino);  
        $scope.displayMode = "preferences"; //cambiar vista  
                
    };

    $scope.deleteFromCart = function(vino){
        $scope.cesta.delete(vino);
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
            method: "POST",
            url: baseUrl + "/" + $scope.id + "/" + wine.id
        }).then(function(response){
            $scope.buyStatus = response.statusText;
            index = $scope.cesta.productos.indexOf(wine);
            $scope.cesta.productos.splice(index, 1);
            $scope.cesta.size--;
            console.log("buy(" + wine.id + ")]: " + response.statusText);
            
        }, function(response){
            $scope.buyStatus = response.statusText;
            console.log("ERROR[buy(" + wine.id + ")]: " + response.statusText);
        });
    };
    
    $scope.getPedidosPendientes = function() {
        $http({ // peticion get
            method: "GET",
            url: baseUrl + "/pedidos/pendientes"
        }).then( // peticion correcta
                function(response){
                    $scope.pedidillos = response.data; // Pedidos[] 
                    console.log("exito: " + response.statusText);
                    console.log("cuerpo: " + response.data);    
                }, // peticion incorrecta
                function(response){
                    $scope.vinosStatus = response.statusText;
                    console.log("error: " + response.statusText);
                });
    }
    
    $scope.deleteOrder = function(pedido) {
        $http({
            method: "DELETE",
            url: baseUrl + "/pedidos/" + pedido.peId
        }).success(function() {
            $scope.pedidillos.splice($scope.products.indexOf(pedido), 1);
        });
    }

    
    $scope.cerrarSesion = function() {
         //$scope.currentProduct = {};
         $scope.displayMode = "login";
    }
});
           