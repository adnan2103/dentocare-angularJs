App.controller('PatientController', ['$scope', '$http', '$routeParams', 'fileUpload', 'patientService', 
	function($scope, $http, $routeParams, fileUpload, patientService) {

    $scope.fetchPatient = function() {
        if ($routeParams.id !== 'new') {
            $http.get('patient/' + $routeParams.id).success(function(patient){
                $scope.patient = patient;
            });
        } else {
            $scope.patient = {
                "contactList": [{}
            ]}
        }
        $scope.random = 232112;
    };

    $scope.gender = [
        {'gender': 'Male'},
        {'gender': 'Female'}
    ];


    $scope.savePatient = function(patient) {
        var promise = patientService.update(patient);

        promise.then(function(response) {
            $scope.patient = response;
            alert('Patient Added/Updated Successfully.');
        }, function(error) {
            alert(error);
            $scope.setError(error);
        }, function(update) {
            alert(update);
            $scope.setError('Got notification: ' + update);
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

    $scope.saveImage = function(id){
        $scope.random = $scope.random + 1;
        var uploadUrl = 'patient/' + id + '/image';
        var promise = fileUpload.uploadFileToUrl($scope.image, uploadUrl);

        promise.then(function(response) {
            $scope.patient.imagePath = uploadUrl + '?random=' + $scope.random;
            $scope.error = true;
            $scope.errorMessage = response;
            alert('Patient image updated successfully.');
        }, function(error) {
            alert(error);
            $scope.setError(error);
        }, function(update) {
            alert(update);
            $scope.setError('Got notification: ' + update);
        });
    };

    $scope.fetchPatient();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
}]);