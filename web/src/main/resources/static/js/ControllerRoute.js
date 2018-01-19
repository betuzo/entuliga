define([
  'jquery',
  'backbone',
  'marionette',
  'subroute',
  'controllers/BaseRouterController',
  'views/public/MainView',
  'views/LoginView',
  'views/public/torneo/TorneoLandingView',
  'views/SignupView',
  'views/private/LigaAdminView',
  'views/private/TorneoAdminView',
  'views/private/EquipoAdminView',
  'views/private/JugadorAdminView',
  'views/private/ArbitroAdminView',
  'views/private/CanchaAdminView',
  'views/private/partido/PartidoAdminView',
  'views/private/perfil/PerfilAdminView',
  'views/private/dashboard/LigasListView'



], function($, Backbone, Mn, Subroute, BaseRouterController, MainView, LoginView, TorneoLandingView, SignupView, LigaAdminView, TorneoAdminView, EquipoAdminView, JugadorAdminView, ArbitroAdminView, CanchaAdminView, PartidoAdminView, PerfilAdminView, LigasListView) {

  var ControllerRoute = BaseRouterController.extend({
    // initialize: function() {
    // },

    start: function() {

    },

    // Action that need authentication and if user is not authenticated
    // gets redirect to login page
    requresAuth: function() {
      return [
        // 'login',
        'home',
        'logout'
      ];
    },

    // Routes that should not be accessible if user is authenticated
    // for example, login, register, forgetpasword ...
    preventAccessWhenAuth: function() {
      return [
        'login',
        // 'home',
        // 'logoulogint'
      ];
    },

    main() {
      this.changeView(new MainView());
    },

    publicTorneo: function(clave) {
      var view = new TorneoLandingView({ clave: clave });
      this.changeView(view);
    },

    home: function() {
      this.changeView(new MainView());
    },

    login: function() {
      this.changeView(new LoginView());
    },

    signup: function() {
      this.changeView(new SignupView());
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

    showDashboard: function(clave) {
      var view = new LigaAdminView();
      this.changeView(view);
    },

    showDashboardConfig: function(clave) {
      var view = new LigasListView();
      this.changeView(view);
    },

    adminPartido: function(partido) {
      var view = new PartidoAdminView();
      view.setIdPartido(partido);
      this.changeView(view);
    },

    // login action
    logout: function() {
      // No callbacks needed b/c of session event listening
      Backbone.Radio.channel('app').request('session').login({ logout: 'logout' }, {
        success: () => {
          Backbone.history.navigate('', { trigger: true });
        },
        complete: () => {}
      });
    }

  });

  return ControllerRoute;
});
