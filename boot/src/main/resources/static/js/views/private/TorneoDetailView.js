define([
	'jquery',
	'backbone',
	'core/BaseView',
	'views/private/TorneoEquipoAdminView',
	'views/private/TorneoJugadorAdminView',
	'text!templates/private/tplTorneoDetail.html'
], function($, Backbone, BaseView, TorneoEquipoAdminView, TorneoJugadorAdminView, tplTorneoDetail){

	var TorneoDetailView = BaseView.extend({
        template: _.template(tplTorneoDetail),

        events: {
            'click #torneo-jornada'     :   'viewJornada',
            'click #torneo-equipo'      :   'viewEquipo',
            'click #torneo-jugador'     :   'viewJugador',
            'click #torneo-partido'     :   'viewPartido',
            'click .list-group-item'    :   'clickOptionDetail'
        },

        initialize: function() {
            this.tipo = 'none';
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        viewJornada: function() {
            if (this.torneoDetailAdminView == 'undefined' || this.torneoDetailAdminView == undefined
                || this.tipo != 'jornada'){
                this.tipo = 'jornada';
            }
        },

        viewPartido: function() {
            if (this.torneoDetailAdminView == 'undefined' || this.torneoDetailAdminView == undefined
                || this.tipo != 'partido'){
                this.tipo = 'partido';
            }
        },

        viewJugador: function() {
            if (this.torneoDetailAdminView == 'undefined' || this.torneoDetailAdminView == undefined
                || this.tipo != 'jugador'){
                this.tipo = 'jugador';
                this.torneoDetailAdminView = new TorneoJugadorAdminView({model: this.model});
                $('#torneo-edit').html(this.torneoDetailAdminView.render().$el);
            }
        },

        viewEquipo: function() {
            if (this.torneoDetailAdminView == 'undefined' || this.torneoDetailAdminView == undefined
                || this.tipo != 'equipo'){
                this.tipo = 'equipo';
                this.torneoDetailAdminView = new TorneoEquipoAdminView({model: this.model});
                $('#torneo-edit').html(this.torneoDetailAdminView.render().$el);
            }
        },

        clickOptionDetail: function(event) {
            $('.list-group-item').removeClass('active');
            $(event.target).addClass('active');
        }
	});

	return TorneoDetailView;

});