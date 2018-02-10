define([
  'jquery',
  'backbone',
  'marionette',
  'subroute',
  'core/ViewManager',
  'views/private/perfil/PerfilAdminView',
  'views/private/LigaAdminView',
  'views/private/TorneoAdminView',
  'views/private/EquipoAdminView',
  'views/private/JugadorAdminView',
  'views/private/ArbitroAdminView',
  'views/private/CanchaAdminView'
], function($, Backbone, Mn, Subroute, ViewManager, PerfilAdminView, LigaAdminView, TorneoAdminView, EquipoAdminView, JugadorAdminView, ArbitroAdminView, CanchaAdminView) {

  var AdminRoute = Backbone.SubRoute.extend({
    routes : {
      '': 'admin',
      'perfil': 'adminPerfil',
      'ligas': 'adminLigas',
      'torneos': 'adminTorneos',
      'torneos/:liga': 'adminTorneos',
      'equipos': 'adminEquipos',
      'jugadores': 'adminJugadores',
      'arbitros': 'adminArbitros',
      'canchas': 'adminCanchas',
      'partido/:partido': 'adminPartido',
    },

    initialize: function() {
      console.log("initialize router admin");
      this.viewMana = new ViewManager();
    },


    admin : function (opt) {
      this.viewMana.showView(new PerfilAdminView());
    },

    adminPerfil: function(){
      this.viewMana.showView(new PerfilAdminView());
    },

    adminLigas: function(){
      var liga = new LigaAdminView();
      this.viewMana.showView(liga);
    },
    adminTorneos: function(){
      this.viewMana.showView(new TorneoAdminView());
    },
    adminEquipos: function(){
      this.viewMana.showView(new EquipoAdminView());
    },
    adminJugadores:  function(){
      this.viewMana.showView(new JugadorAdminView());
    },
    adminArbitros: function(){
      this.viewMana.showView(new ArbitroAdminView());
    },
    adminCanchas: function(){
      this.viewMana.showView(new CanchaAdminView());
    }
  });

  return AdminRoute;
});
