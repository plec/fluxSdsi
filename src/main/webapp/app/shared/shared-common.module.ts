import { NgModule } from '@angular/core';

import { FluxSdsiSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [FluxSdsiSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [FluxSdsiSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class FluxSdsiSharedCommonModule {}
