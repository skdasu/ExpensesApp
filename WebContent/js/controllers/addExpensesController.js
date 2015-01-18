/**
 * 
 */
var addExpenseSectionControllers = angular.module('addExpenseSectionControllers', []);
 
addExpenseSectionControllers.controller('AddExpenseListCtrl', function($scope, $http) {
    //$scope.entry = {};
	$scope.receipt = {};
	
    $scope.receipt.entries = [];
    //$scope.entry.isOrganic = 'false';
    $scope.categories = {};
    $scope.subCategories = {};
    $scope.merchants = {};
    
    $scope.getCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getCategories").
        success(function(data, status, headers, config) {
            //console.log(":::::: category Success response :::::::"+data);
            $scope.categories = data;
        });
    }
    $scope.getSubCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getSubCategories").
        success(function(data, status, headers, config) {
            //console.log(":::::: Subcategory Success response :::::::"+data);
            $scope.subCategories = data;
        });
    }
    $scope.getMerchants = function(){
        $http.get("http://localhost:8090/ExpensesApp/getMerchants").
        success(function(data, status, headers, config) {
            //console.log(":::::: merchants Success response :::::::"+data);
            $scope.merchants = data;
        });
    }    
    
    $scope.addEntry = function(){
    	//console.log("::::::::::::::::: add expenses called ::::::::::::::::::::");
    	var newEntry = {};
    	newEntry.isOrganic = 'false';
    	$scope.receipt.entries.push(newEntry);
    }
    
    $scope.calculateRate = function(index){
    	//console.log("calculate rate::: index:: "+index + "  price  "+$scope.receipt.entries[index].price + "  quantity "+$scope.receipt.entries[index].quantity);
    	$scope.receipt.entries[index].rate = ($scope.receipt.entries[index].price * (1/$scope.receipt.entries[index].quantity));
        //$scope.entry.rate = ($scope.entry.price * (1/$scope.entry.quantity));
    }
    
    $scope.addExpense = function(){
    	var newExpenseItem = angular.toJson($scope.receipt);
    	console.log("::::::::::::::::: add expenses called ::::::::::::::::::::"+newExpenseItem);
        //newExpenseItem
    }
    
    $scope.getMerchants();
    $scope.getCategory();
    $scope.getSubCategory();
    
    /*$scope.addExpense = function(){
    console.log("::::::::::::::::: add expenses called ::::::::::::::::::::");
    //newExpenseItem
    var newExpenseItem = angular.toJson($scope.entry);
    console.log("\n\n::::::::::::Data Passed::::::::::::"+newExpenseItem);
    $http({
        method: 'POST', 
        url: 'http://localhost:8090/ExpensesApp/addExpense', 
        params:  {
            newExpenseItem: newExpenseItem
        }
    }).
    success(function (data, status, headers, config) {
        console.log(":::::::::::: Success ::::::::::::"+data);
        if(data == "1"){
            $scope.entry = {};
            $scope.entry.isOrganic = 'false';
            $scope.getExpenses();
        }
    }).
    error(function (data, status, headers, config) {
        console.log(":::::::::::: Failure ::::::::::::"+data);
    });

}

*/
    
});