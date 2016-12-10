var DentoCareAppFactory = angular.module('DentoCareApp.factory', ['ngResource']);

DentoCareAppFactory.factory("Post", ['$resource', function($resource) {
    return $resource("/patients");
}]);

DentoCareAppFactory.factory("Treatments", ['$resource', function($resource) {
    return $resource("/treatments");
}]);