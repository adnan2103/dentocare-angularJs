'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http) {
    $scope.fetchAllPatients = function() {
        $http.get('patient/all').success(function(allpatients){
            $scope.patients = allpatients;
        });
    };

    $scope.fetchAllPatients();
};