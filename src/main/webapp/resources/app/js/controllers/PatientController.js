'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http) {
    $scope.fetchPatient = function() {
        $http.get('patient/detail').success(function(patient){
            $scope.patient = patient;
        });
    };

    $scope.fetchPatient();
};