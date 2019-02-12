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
