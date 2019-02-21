import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'ref-environnement',
                loadChildren: './ref-environnement/ref-environnement.module#FluxSdsiRefEnvironnementModule'
            },
            {
                path: 'ref-site',
                loadChildren: './ref-site/ref-site.module#FluxSdsiRefSiteModule'
            },
            {
                path: 'ref-domaine',
                loadChildren: './ref-domaine/ref-domaine.module#FluxSdsiRefDomaineModule'
            },
            {
                path: 'ref-flux',
                loadChildren: './ref-flux/ref-flux.module#FluxSdsiRefFluxModule'
            },
            {
                path: 'ref-type-fonction',
                loadChildren: './ref-type-fonction/ref-type-fonction.module#FluxSdsiRefTypeFonctionModule'
            },
            {
                path: 'ref-fonction',
                loadChildren: './ref-fonction/ref-fonction.module#FluxSdsiRefFonctionModule'
            },
            {
                path: 'ref-zone',
                loadChildren: './ref-zone/ref-zone.module#FluxSdsiRefZoneModule'
            },
            {
                path: 'ref-numero',
                loadChildren: './ref-numero/ref-numero.module#FluxSdsiRefNumeroModule'
            },
            {
                path: 'flux',
                loadChildren: './flux/flux.module#FluxSdsiFluxModule'
            },
            {
                path: 'ref-environnement',
                loadChildren: './ref-environnement/ref-environnement.module#FluxSdsiRefEnvironnementModule'
            },
            {
                path: 'ref-site',
                loadChildren: './ref-site/ref-site.module#FluxSdsiRefSiteModule'
            },
            {
                path: 'ref-domaine',
                loadChildren: './ref-domaine/ref-domaine.module#FluxSdsiRefDomaineModule'
            },
            {
                path: 'ref-flux',
                loadChildren: './ref-flux/ref-flux.module#FluxSdsiRefFluxModule'
            },
            {
                path: 'ref-type-fonction',
                loadChildren: './ref-type-fonction/ref-type-fonction.module#FluxSdsiRefTypeFonctionModule'
            },
            {
                path: 'ref-fonction',
                loadChildren: './ref-fonction/ref-fonction.module#FluxSdsiRefFonctionModule'
            },
            {
                path: 'ref-zone',
                loadChildren: './ref-zone/ref-zone.module#FluxSdsiRefZoneModule'
            },
            {
                path: 'ref-numero',
                loadChildren: './ref-numero/ref-numero.module#FluxSdsiRefNumeroModule'
            },
            {
                path: 'flux',
                loadChildren: './flux/flux.module#FluxSdsiFluxModule'
            },
            {
                path: 'ref-environnement',
                loadChildren: './ref-environnement/ref-environnement.module#FluxSdsiRefEnvironnementModule'
            },
            {
                path: 'ref-site',
                loadChildren: './ref-site/ref-site.module#FluxSdsiRefSiteModule'
            },
            {
                path: 'ref-domaine',
                loadChildren: './ref-domaine/ref-domaine.module#FluxSdsiRefDomaineModule'
            },
            {
                path: 'ref-flux',
                loadChildren: './ref-flux/ref-flux.module#FluxSdsiRefFluxModule'
            },
            {
                path: 'ref-type-fonction',
                loadChildren: './ref-type-fonction/ref-type-fonction.module#FluxSdsiRefTypeFonctionModule'
            },
            {
                path: 'ref-fonction',
                loadChildren: './ref-fonction/ref-fonction.module#FluxSdsiRefFonctionModule'
            },
            {
                path: 'ref-zone',
                loadChildren: './ref-zone/ref-zone.module#FluxSdsiRefZoneModule'
            },
            {
                path: 'ref-numero',
                loadChildren: './ref-numero/ref-numero.module#FluxSdsiRefNumeroModule'
            },
            {
                path: 'flux',
                loadChildren: './flux/flux.module#FluxSdsiFluxModule'
            },
            {
                path: 'ref-environement',
                loadChildren: './ref-environement/ref-environement.module#FluxSdsiRefEnvironementModule'
            },
            {
                path: 'ref-site',
                loadChildren: './ref-site/ref-site.module#FluxSdsiRefSiteModule'
            },
            {
                path: 'ref-vlan',
                loadChildren: './ref-vlan/ref-vlan.module#FluxSdsiRefVlanModule'
            },
            {
                path: 'ref-domaine',
                loadChildren: './ref-domaine/ref-domaine.module#FluxSdsiRefDomaineModule'
            },
            {
                path: 'ref-type-flux',
                loadChildren: './ref-type-flux/ref-type-flux.module#FluxSdsiRefTypeFluxModule'
            },
            {
                path: 'ref-type-fonction',
                loadChildren: './ref-type-fonction/ref-type-fonction.module#FluxSdsiRefTypeFonctionModule'
            },
            {
                path: 'ref-fonction',
                loadChildren: './ref-fonction/ref-fonction.module#FluxSdsiRefFonctionModule'
            },
            {
                path: 'ref-zone',
                loadChildren: './ref-zone/ref-zone.module#FluxSdsiRefZoneModule'
            },
            {
                path: 'ref-numero',
                loadChildren: './ref-numero/ref-numero.module#FluxSdsiRefNumeroModule'
            },
            {
                path: 'demande-flux',
                loadChildren: './demande-flux/demande-flux.module#FluxSdsiDemandeFluxModule'
            },
            {
                path: 'ref-type-authorisation',
                loadChildren: './ref-type-authorisation/ref-type-authorisation.module#FluxSdsiRefTypeAuthorisationModule'
            },
            {
                path: 'ref-matrice-flux',
                loadChildren: './ref-matrice-flux/ref-matrice-flux.module#FluxSdsiRefMatriceFluxModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiEntityModule {}
