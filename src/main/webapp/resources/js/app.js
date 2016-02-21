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
}]);
