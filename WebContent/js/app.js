'use strict';

/* App Module */
var expensesApp = angular.module('expensesApp', [
  'ngRoute',
  'navigationController',
  'expenseSectionControllers',
  'categoriesSectionControllers',
  'merchantSectionControllers'
]);
 
expensesApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/expenses', {
        templateUrl: 'partials/expenses/expenses-list.html',
        controller: 'ExpenseListCtrl'
      }).
      when('/category', {
        templateUrl: 'partials/expenses/category-list.html',
        controller: 'CategoryListCtrl'
      }).
      when('/merchants', {
        templateUrl: 'partials/expenses/list-merchants.html',
        controller: 'MerchantListCtrl'
      }).
      otherwise({
        redirectTo: '/expenses'
      });
  }]);