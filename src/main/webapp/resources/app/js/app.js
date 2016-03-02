'use strict';

var DentoCareApp = {};

var App = angular.module('DentoCareApp', ['ngRoute','auth','navigation','DentoCareApp.filters', 'DentoCareApp.services', 'DentoCareApp.directives', 'DentoCareApp.factory']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider.when('/patients', {
        templateUrl: 'patients/layout',
        controller: PatientsController
    }).when('/patient/detail', {
        templateUrl : 'patient/layout',
        controller : PatientController
    }).when('/treatment', {
        templateUrl : 'treatment/layout',
        controller : TreatmentController
    }).when('/login', {
        templateUrl : 'login/layout',
        controller : 'navigation'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).filter('searchForName', function(){

    // All filters must return a function. The first parameter
    // is the data that is to be filtered, and the second is an
    // argument that may be passed with a colon (searchFor:searchString)

    return function(arr, searchName){

        if(!searchName){
            return arr;
        }

        var result = [];

        searchName = searchName.toLowerCase();

        // Using the forEach helper method to loop through the array
        angular.forEach(arr, function(patients){

            if(patients.name.toLowerCase().indexOf(searchName) !== -1){
                result.push(patients);
            }

        });

        return result;
    };

}).filter('searchForNumber', function(){

    // All filters must return a function. The first parameter
    // is the data that is to be filtered, and the second is an
    // argument that may be passed with a colon (searchFor:searchString)

    return function(arr, searchNumber){

        if(!searchNumber){
            return arr;
        }

        var result = [];

        searchNumber = searchNumber.toLowerCase();

        // Using the forEach helper method to loop through the array
        angular.forEach(arr, function(patients){

            if(patients.phoneNumber.toLowerCase().indexOf(searchNumber) !== -1){
                result.push(patients);
            }

        });

        return result;
    };

}).run(function(auth) {

    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/patients', '/login', '/logout');

});



