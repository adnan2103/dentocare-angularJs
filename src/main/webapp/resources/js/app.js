'use strict';

var DentoCareApp = {};

var App = angular.module('DentoCareApp', ['DentoCareApp.filters', 'DentoCareApp.services', 'DentoCareApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/patients', {
        templateUrl: 'patients/layout',
        controller: PatientController
    });

    $routeProvider.otherwise({redirectTo: '/patients'});
}]);
