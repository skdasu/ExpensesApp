'use strict';

/* Controllers */
var navigationController = angular.module('navigationController', []);
 
navigationController.controller('navigationCtrl', ['$scope', '$location',
  function ($scope, $location) {

    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };

  }]);

var expenseSectionControllers = angular.module('expenseSectionControllers', []);
 
expenseSectionControllers.controller('ExpenseListCtrl', function($scope, $http) {
    $scope.expenses = {};
    $scope.categories = {};
    $scope.subCategories = {};
    $scope.merchants = {};
    $scope.entry = {};
    $scope.entry.isOrganic = 'false';

    $scope.getExpenses = function () {
        $http.get('http://localhost:8090/ExpensesApp/getExpenses').success(function(data) {
            console.log(":::::: expenses Success response :::::::"+data);
            $scope.expenses = data;
        });
    }
    $scope.getCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getCategories").
        success(function(data, status, headers, config) {
            console.log(":::::: category Success response :::::::"+data);
            $scope.categories = data;
        });
    }
    $scope.getSubCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getSubCategories").
        success(function(data, status, headers, config) {
            console.log(":::::: Subcategory Success response :::::::"+data);
            $scope.subCategories = data;
        });
    }
    $scope.getMerchants = function(){
        $http.get("http://localhost:8090/ExpensesApp/getMerchants").
        success(function(data, status, headers, config) {
            console.log(":::::: merchants Success response :::::::"+data);
            $scope.merchants = data;
        });
    }

    $scope.addExpense = function(){
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

    $scope.calculateRate = function(){
        $scope.entry.rate = ($scope.entry.price * (1/$scope.entry.quantity));
    }

    $scope.orderProp = 'dateOfPurchase';
    $scope.getExpenses();
    $scope.getMerchants();
    $scope.getCategory();
    $scope.getSubCategory();
});



var categoriesSectionControllers = angular.module('categoriesSectionControllers', []);
 
categoriesSectionControllers.controller('CategoryListCtrl', function($scope, $http) {
    $scope.newCategory = {};
    $scope.newSubCategory = {};
    $scope.categories = null;
    $scope.subCategories = null;

    $scope.getCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getCategories").
        success(function(data, status, headers, config) {
            console.log(":::::: category Success response :::::::"+data);
            $scope.categories = data;
        });
    }

    $scope.getSubCategory = function(){
        $http.get("http://localhost:8090/ExpensesApp/getSubCategories").
        success(function(data, status, headers, config) {
            console.log(":::::: Subcategory Success response :::::::"+data);
            $scope.subCategories = data;
        });
    }

    $scope.addCategory = function(){
        console.log("\n\n::::::::::::Data Passed::::::::::::"+$scope.newCategory.name);
        $http({
            method: 'POST', 
            url: 'http://localhost:8090/ExpensesApp/addCategory', 
            params:  {
                newCategory: $scope.newCategory.name
            }
        }).
        success(function (data, status, headers, config) {
            console.log(":::::::::::: Success ::::::::::::"+data);
            if(data == "1"){
                $scope.getCategory();
            }
        }).
        error(function (data, status, headers, config) {
            console.log(":::::::::::: Failure ::::::::::::"+data);
        });
        $scope.newCategory.name = '';
    }

    $scope.addSubCategory = function(){
        console.log("called add sub category");
        var newSubcategoryData = angular.toJson($scope.newSubCategory);
        console.log("\n\n::::::::::::Data Passed::::::::::::"+newSubcategoryData);
        $http({
            method: 'POST', 
            url: 'http://localhost:8090/ExpensesApp/addSubCategories', 
            params:  {
                newSubCategory: newSubcategoryData
            }
        }).
        success(function (data, status, headers, config) {
            console.log(":::::::::::: Success ::::::::::::"+data);
            if(data == "1"){
                $scope.newSubCategory = {};
                $scope.getSubCategory();
            }
        }).
        error(function (data, status, headers, config) {
            console.log(":::::::::::: Failure ::::::::::::"+data);
        });
    }

    $scope.orderProp = 'categoryID';
    $scope.getCategory();
    $scope.getSubCategory();
});
  /*function ($scope, $http) {
    $http.get('expensesData/category.json').success(function(data) {
      $scope.categories = data;
    });
 
    $scope.orderProp = 'catindex';
  }]);*/

var merchantSectionControllers = angular.module('merchantSectionControllers', []);
 
merchantSectionControllers.controller('MerchantListCtrl', function($scope, $http) {
    $scope.newMerchant = {};
    $scope.merchants = null;
    
    $http.get("http://localhost:8090/ExpensesApp/getMerchants").
    success(function(data, status, headers, config) {
        $scope.merchants = data;
    });

    $scope.addNewMerchant = function(){
        console.log("\n\n::::::::::::Data Passed::::::::::::"+$scope.newMerchant.name);
        var myData = {};
        myData.dataItem = $scope.newMerchant.name;
        myData = angular.toJson(myData);
        console.log("\n\n::::::::::::Data Passed::::::::::::"+myData);
        $http({
            method: 'POST', 
            url: 'http://localhost:8090/ExpensesApp/addMerchants', 
            params:  {
                newMerchant: $scope.newMerchant.name
            }
            //headers: {'Content-Type': 'application/json'}
        }).
        success(function (data, status, headers, config) {
            //$scope.status=data;
            console.log(":::::::::::: Success ::::::::::::"+data);
        }).
        error(function (data, status, headers, config) {
            //$scope.status=data;
            console.log(":::::::::::: Failure ::::::::::::"+data);
        });
        $scope.newMerchant.name = '';
    };

    $scope.orderProp = 'merchantID';
});