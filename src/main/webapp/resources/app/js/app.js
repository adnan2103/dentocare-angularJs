'use strict';

var DentoCareApp = {};

var App = angular.module('DentoCareApp', ['srph.age-filter','jkuri.datepicker', 'ngRoute','auth','navigation','DentoCareApp.filters', 'DentoCareApp.services', 'DentoCareApp.directives', 'DentoCareApp.factory', 'ngResource']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider.when('/patients', {
        templateUrl: 'patients/layout',
        controller: PatientsController
    }).when('/patient/:id', {
        templateUrl : 'patient/layout',
        controller : PatientController
    }).when('/patient/:id/treatment', {
        templateUrl : 'treatment/layout',
        controller : TreatmentController
    }).when('/login', {
        templateUrl : 'login/layout',
        controller : 'navigation'
    }).when('/treatment/:id/:type/images/:count', {
        templateUrl : 'treatment-images/layout',
        controller : TreatmentImageController
    }).otherwise('/patients');

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

}).directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        },
        controller : PatientController
    };
}]).service('fileUpload', ['$http','$q', function ($http, $q) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var deferred = $q.defer();

        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function(){
                deferred.resolve("Patient photo uploaded successfully.");
            })
            .error(function(message){
                deferred.reject("Patient photo upload failed. : " + message);
            });
        return deferred.promise;
    }
}]).service('patientService', ['$resource', function ($resource) {

    return $resource('patient/save', {}, {

        'update': { method:'PUT' }
    });


}]).run(function(auth) {

    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/', '/login', '/logout');

});



