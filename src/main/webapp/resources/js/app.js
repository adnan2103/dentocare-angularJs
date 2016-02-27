'use strict';

var DentoCareApp = {};

var App = angular.module('DentoCareApp', ['DentoCareApp.filters', 'DentoCareApp.services', 'DentoCareApp.directives', 'DentoCareApp.factory']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/patient', {
        templateUrl: 'patient/layout',
        controller: PatientController
    });

    $routeProvider.otherwise({redirectTo: '/patient'});
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

});


