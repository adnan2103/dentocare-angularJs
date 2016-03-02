angular.module('auth', []).factory(
		'auth',

		function($rootScope, $http, $location) {

			enter = function() {
				if ($location.path() != auth.loginPath) {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				}					
			}

			var auth = {

				authenticated : false,

				loginPath : '/login',
				logoutPath : '/logout',
				homePath : '/patient',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};

					$http.get('patient/all', {
						headers : headers
					}).success(function(data) {
						console.log('Adnan data : '+data);
						if (data) {
							auth.authenticated = true;
						} else {
							auth.authenticated = false;
						}
						callback && callback(auth.authenticated);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}).error(function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},

				clear : function() {
					$location.path(auth.loginPath);
					auth.authenticated = false;
					$http.post(auth.logoutPath, {}).success(function() {
						console.log("Logout succeeded");
					}).error(function(data) {
						console.log("Logout failed : " + data);
					});
				},

				init : function(homePath, loginPath, logoutPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;
					auth.logoutPath = logoutPath;

					auth.authenticate({}, function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
						}
					})

					// Guard route changes and switch to login page if unauthenticated
					$rootScope.$on('$routeChangeStart', function() {
						enter();
					});

				}

			};

			return auth;

		});
