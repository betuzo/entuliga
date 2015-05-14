define([
	'jquery',
	'bootflat',
	'selecter',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'text!templates/private/tplLigaAdmin.html'
], function($, bootflat, selecter, BaseView, MainColoniaAdminView, tplLigaAdmin){

	var LigaAdminView = BaseView.extend({
        template: _.template(tplLigaAdmin),

        events: {
            'click #colonia-buscar': 'buscarColonia'
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_liga").selecter();
            return this;
        },

        buscarColonia: function() {
            new MainColoniaAdminView();
        }
	});

	return LigaAdminView;

});