/**
 * 
 */
var addExpenseSectionControllers = angular.module('addExpenseSectionControllers', []);
 
/*addExpenseSectionControllers.controller('AddExpenseListCtrl', function($scope, $http) {
    $scope.entry = {};
    $scope.entry.isOrganic = 'false';

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
    
});*/