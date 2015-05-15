define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'views/private/LigaDetailView',
	'views/private/LigaEditView',
	'text!templates/private/tplLigaAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, LigaDetailView, LigaEditView, tplLigaAdmin){

	var LigaAdminView = BaseView.extend({
        template: _.template(tplLigaAdmin),

        events: {
            'change #select-liga': 'changeLiga',
            'click #liga-nuevo': 'newLiga',
            'click #liga-editar': 'editLiga'
        },

        initialize: function() {

        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_liga").select();
            return this;
        },

        changeLiga: function(event) {

        },

        newLiga: function() {
            this.ligaEditView = new LigaEditView({tipo: 'new', modelo: null});
            $('#liga-edit').html(this.ligaEditView.render().$el);
        },

        editLiga: function() {

        }
	});

	return LigaAdminView;

});