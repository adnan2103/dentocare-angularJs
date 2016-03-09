'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http, $routeParams) {
    $scope.fetchPatient = function() {
        if ($routeParams.id !== 'new') {
            $http.get('patient/' + $routeParams.id).success(function(patient){
                $scope.patient = patient;
            });
        } else {
            $scope.patient = {};
        }

    };

    $scope.savePatient = function(patient) {
        $scope.resetError();

        $http.put('patient/save', patient).success(function(patient) {
            $scope.patient = patient;
            $scope.message = 'Patient Created / Updated.';
        }).error(function() {
            $scope.setError('Could not create / update the patient.');
        });
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchPatient();
};