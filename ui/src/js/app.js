'use strict';

var DentoCareApp = {};

var App = angular.module('DentoCareApp', ['angularFileUpload','jkuri.datepicker', 'ngRoute','auth','navigation','DentoCareApp.filters', 'DentoCareApp.services', 'DentoCareApp.directives', 'DentoCareApp.factory', 'ngResource', 'ui.calendar']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider.when('/patients', {
        templateUrl: 'patients/layout',
        controller: PatientsController
    }).when('/patient/:id', {
        templateUrl : 'patient/layout',
        controller : PatientController
    }).when('/patients/:id/appointments', {
        templateUrl : 'appointments/layout',
        controller : AppointmentsController
    }).when('/patient/:id/treatment', {
        templateUrl : 'treatment/layout',
        controller : TreatmentController
    }).when('/login', {
        templateUrl : 'login/layout',
        controller : 'navigation'
    }).when('/treatment/:id/:type/images/:count', {
        templateUrl : 'treatment-images/layout',
        controller : TreatmentImageController
    }).when('/treatments', {
        templateUrl : 'treatments/layout',
        controller : TreatmentsController
    }).when('/administration', {
        templateUrl : 'administration/layout',
        controller : AdministrationController
    }).otherwise('/patients');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).constant('appConfiguration', {
    xAuthTokenHeaderName: 'x-auth-token',
    basicAuthHeaderName: 'authorization'
}).filter('searchOnKey', function(){

    return function(arr, searchKey){

        if(!searchKey){
            return arr;
        }

        var result = [];

        searchKey = searchKey.toLowerCase();

        angular.forEach(arr, function(patients){

            if(angular.isString(patients.name) && patients.name.toLowerCase().indexOf(searchKey) !== -1){
                result.push(patients);
            }

            if(angular.isString(patients.gender) && patients.gender.toLowerCase() === searchKey){
                result.push(patients);
            }

            if(angular.isString(patients.contactList[0].primaryPhoneNumber) && patients.contactList[0].primaryPhoneNumber.toLowerCase().indexOf(searchKey) !== -1){
                result.push(patients);
            }

            if(angular.isString(patients.contactList[0].secondaryPhoneNumber) && patients.contactList[0].secondaryPhoneNumber.toLowerCase().indexOf(searchKey) !== -1){
                result.push(patients);
            }

            if(angular.isString(patients.contactList[0].email) && patients.contactList[0].email.toLowerCase().indexOf(searchKey) !== -1){
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
}]).service('patientService', ['$http','$q', function ($http, $q) {

    this.update = function(patient){
        var deferred = $q.defer();

        $http.put('patient', patient, {})
            .success(function(response){
                deferred.resolve(response);
            })
            .error(function(message){
                deferred.reject(message);
            });
        return deferred.promise;
    }

}]).service('appointmentService', ['$http','$q', function ($http, $q) {

    this.save = function(appointment){
        var deferred = $q.defer();

        $http.put('appointment', appointment, {})
            .success(function(response){
                deferred.resolve(response);
            })
            .error(function(message){
                deferred.reject(message);
            });
        return deferred.promise;
    }

}]).directive('ngThumb', ['$window', function($window) {
    var helper = {
        support: !!($window.FileReader && $window.CanvasRenderingContext2D),
        isFile: function(item) {
            return angular.isObject(item) && item instanceof $window.File;
        },
        isImage: function(file) {
            var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    };

    return {
        restrict: 'A',
        template: '<canvas/>',
        link: function(scope, element, attributes) {
            if (!helper.support) return;

            var params = scope.$eval(attributes.ngThumb);

            if (!helper.isFile(params.file)) return;
            if (!helper.isImage(params.file)) return;

            var canvas = element.find('canvas');
            var reader = new FileReader();

            reader.onload = onLoadFile;
            reader.readAsDataURL(params.file);

            function onLoadFile(event) {
                var img = new Image();
                img.onload = onLoadImage;
                img.src = event.target.result;
            }

            function onLoadImage() {
                var width = params.width || this.width / this.height * params.height;
                var height = params.height || this.height / this.width * params.width;
                canvas.attr({ width: width, height: height });
                canvas[0].getContext('2d').drawImage(this, 0, 0, width, height);
            }
        }
    };
}]).run(function(auth) {

    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/', '/login', '/logout');

});



