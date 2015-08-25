define([
	'jquery',
	'backbone',
	'core/BaseView',
	'views/private/TorneoEquipoAdminView',
	'views/private/TorneoJugadorAdminView',
    'views/private/TorneoJornadaAdminView',
    'views/private/TorneoPartidoAdminView',
	'text!templates/private/tplTorneoDetail.html'
], function($, Backbone, BaseView, TorneoEquipoAdminView, TorneoJugadorAdminView,
            TorneoJornadaAdminView, TorneoPartidoAdminView, tplTorneoDetail){

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
                this.torneoDetailAdminView = new TorneoJornadaAdminView({model: this.model});
                $('#torneo-edit').html(this.torneoDetailAdminView.render().$el);
            }
        },

        viewPartido: function() {
            if (this.torneoDetailAdminView == 'undefined' || this.torneoDetailAdminView == undefined
                || this.tipo != 'partido'){
                this.tipo = 'partido';
                this.torneoDetailAdminView = new TorneoPartidoAdminView({model: this.model});
                $('#torneo-edit').html(this.torneoDetailAdminView.render().$el);
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