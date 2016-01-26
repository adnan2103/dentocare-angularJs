'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http) {
    $scope.fetchAllPatients = function() {
        $http.get('patients/all').success(function(allpatients){
            $scope.patients = allpatients;
        });
    };

    $scope.fetchAllPatients();
};