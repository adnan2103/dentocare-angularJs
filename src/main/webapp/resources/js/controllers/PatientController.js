'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http, Post) {
    /*$scope.fetchAllPatients = function() {
        $http.get('patient/all').success(function(allpatients){
            $scope.patients = allpatients;
        });
    };*/

    Post.query(function(data) {
        $scope.patients = data;
    });
    //$scope.fetchAllPatients();
};