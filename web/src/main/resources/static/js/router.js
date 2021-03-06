define([
  'jquery',
  'underscore',
  'backbone',
  'core/BaseRouter',
  'views/LoginView',
  'views/SignupView',
  'views/TokenValidateView',
  'views/TokenChangePassView',
  'views/private/perfil/PerfilAdminView',
  'views/private/MainAdminNavView',
  'views/private/LigaAdminView',
  'views/private/TorneoAdminView',
  'views/private/EquipoAdminView',
  'views/private/JugadorAdminView',
  'views/private/ArbitroAdminView',
  'views/private/CanchaAdminView',
  'views/private/partido/PartidoAdminView',
  'views/public/MainView',
  'views/public/MainNavView',
  'views/public/torneo/TorneoLandingView',
  'routers/DashboardRoute',
  'routers/AdminRoute',
  'views/private/dashboard/LigasListView'
], function($, _, Backbone, BaseRouter, LoginView, SignupView,
  TokenValidateView, TokenChangePassView, PerfilAdminView,
  MainAdminNavView, LigaAdminView, TorneoAdminView,
  EquipoAdminView, JugadorAdminView, ArbitroAdminView,
  CanchaAdminView, PartidoAdminView, MainView, MainNavView,
  TorneoLandingView, DashboardRoute, AdminRoute, LigasListView) {
  var Router = BaseRouter.extend({

    routes: {
      '': 'main',
      '/': 'main',
      '/#': 'main',
      'login': 'login',
      'signup': 'signup',
      'token/:token': 'token',
      'change/:token': 'changeToken',
      'admin': 'admin',
      'admin/perfil': 'adminPerfil',
      'admin/ligas': 'adminLigas',
      'admin/torneos': 'adminTorneos',
      'admin/torneos/:liga': 'adminTorneos',
      'admin/equipos': 'adminEquipos',
      'admin/jugadores': 'adminJugadores',
      'admin/arbitros': 'adminArbitros',
      'admin/canchas': 'adminCanchas',
      'admin/partido/:partido': 'adminPartido',
      // 'admin*subroute': 'showAdminView',
      'torneo/:clave': 'publicTorneo',
      'dashboard': 'showDashboard',
      'dashboard/config': 'showDashboardConfig'
      // 'dashboard*subroute': 'showDashboard'
    },

    requresAuth: ['#admin'],

    preventAccessWhenAuth: ['#login'],

    initialize: function() {},

    before: function(params, next) {
      console.log("BEFORE ROUTER");
      //Checking if user is authenticated or not
      //then check the path if the path requires authentication
      var isAuth = Backbone.Radio.channel('app').request('session').get('logged_in');
      var path = Backbone.history.location.hash;
      var needAuth = path.indexOf(this.requresAuth) > -1;
      var cancleAccess = _.contains(this.preventAccessWhenAuth, path);


      if(needAuth && !isAuth) {
        //If user gets redirect to login because wanted to access
        // to a route that requires login, save the path in session
        // to redirect the user back to path after successful login
        Backbone.history.navigate('login', { trigger: true });
      } else if(isAuth && cancleAccess) {
        //User is authenticated and tries to go to login, register ...
        // so redirect the user to home page
        Backbone.history.navigate('', { trigger: true });
      } else {
        //No problem, handle the route!!

        // return next();
      }
    },

    after: function() {
      console.log('AFTER ROUTER');
    },

    changeView: function(view) {
      function setView(view) {
        if(this.currentView) {
          if(typeof this.currentView.close === "function") {
            this.currentView.close();
          }else{
            if(typeof this.currentView.destroy === "function") {
              this.currentView.destroy(); //cerrar vistas de marionette
            }
          }
        }
        this.currentView = view;
        $('#container-body').html(view.render().$el);
      }
      setView(view);
    },


    // showDashboard: function(subroute) {
    //   new DashboardRoute('dashboard');
    // },

    showAdminView: function(subroute) {
      new AdminRoute('admin');
    },


    main: function() {
      var view = new MainView();
      this.changeView(view);

    },

    login: function() {
      var view = new LoginView();
      this.changeView(view);
    },

    signup: function() {
      var view = new SignupView();
      this.changeView(view);
    },

    token: function(token) {
      new MainNavView();
      var view = new TokenValidateView({ token: token });
      this.changeView(view);
    },

    changeToken: function(token) {
      new MainNavView();
      var view = new TokenChangePassView({ token: token });
      this.changeView(view);
    },

    admin: function() {
      var view = new PerfilAdminView();
      this.changeView(view);
    },

    adminPerfil: function() {
      var view = new PerfilAdminView();
      this.changeView(view);
    },

    adminLigas: function() {
      var view = new LigaAdminView();
      this.changeView(view);
    },

    adminTorneos: function() {
      var view = new TorneoAdminView();
      this.changeView(view);
    },

    adminEquipos: function() {
      var view = new EquipoAdminView();
      this.changeView(view);
    },

    adminJugadores: function() {
      var view = new JugadorAdminView();
      this.changeView(view);
    },

    adminArbitros: function() {
      var view = new ArbitroAdminView();
      this.changeView(view);
    },

    adminCanchas: function() {
      var view = new CanchaAdminView();
      this.changeView(view);
    },

    adminPartido: function(partido) {
      var view = new PartidoAdminView();
      view.setIdPartido(partido);
      this.changeView(view);
    },

    publicTorneo: function(clave) {
      var view = new TorneoLandingView({ clave: clave });
      this.changeView(view);
    },

    //dashboard

    showDashboard: function(clave) {
      var view = new LigaAdminView();
      this.changeView(view);
    },

    showDashboardConfig: function(clave) {
      var view = new LigasListView();
      this.changeView(view);
    }
  });

  return Router;

});
