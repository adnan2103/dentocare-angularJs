'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http, $routeParams, fileUpload) {

    $scope.fetchPatient = function() {
        if ($routeParams.id !== 'new') {
            $http.get('patient/' + $routeParams.id).success(function(patient){
                $scope.patient = patient;
                $scope.photo = 'patient/'+patient.id+'/photo';
                $scope.photoURL = $scope.photo + '?' + new Date().getTime();
            });
        } else {
            $scope.patient = {};
        }

    };

    $scope.gender = [
        {'gender': 'Male'},
        {'gender': 'Female'}
    ];

    $scope.savePatient = function(patient) {
        $scope.resetError();

        $http.put('patient/save', patient).success(function(patient) {
            $scope.patient = patient;
            $scope.message = 'Patient Created / Updated.';
            $scope.photo = 'patient/'+patient.id+'/photo';
            $scope.photoURL = $scope.photo + '?' + new Date().getTime();
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

    $scope.uploadPhoto = function(patient){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = 'patient/' + patient.id + '/photo'
        fileUpload.uploadFileToUrl(file, uploadUrl);
        $scope.message = 'Photo Uploaded Successfully.';
        $scope.photo = 'patient/'+patient.id+'/photo';
        $scope.photoURL = $scope.photo + '?' + new Date().getTime();
    };

    $scope.$watch('photoURL', function() {
        alert('Photo uploaded Successfully.');
    });

    $scope.fetchPatient();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
};