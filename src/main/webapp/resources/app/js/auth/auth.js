angular.module('auth', []).factory(
		'auth',

		function($rootScope, $http, $location, $log, appConfiguration) {

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
				homePath : '/',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};

					$http.get('/user/authenticate', {
						headers : headers
					}).success(function(user) {
						if (user) {
							user['isAuthenticated']=true;
							auth.authenticated = true;

						} else {
							user['isAuthenticated']=false;
							auth.authenticated = false;
						}
						callback && callback(user);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}).error(function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},

				clear : function() {
					$log.info('Logging out...');
					$location.path(auth.loginPath);
					auth.authenticated = false;
					delete $rootScope.user;
					delete $http.defaults.headers.common[appConfiguration.basicAuthHeaderName];
					$http.get('/login/layout', {}).success(function() {
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
