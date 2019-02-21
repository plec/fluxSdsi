package fr.gouv.culture.sdsi.reseau.flux.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefSite.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefDomaine.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction.class.getName() + ".codeZones", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefZone.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefZone.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.Flux.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironement.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironement.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefVlan.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefVlan.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFlux.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFlux.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.DemandeFlux.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeAuthorisation.class.getName(), jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeAuthorisation.class.getName() + ".codes", jcacheConfiguration);
            cm.createCache(fr.gouv.culture.sdsi.reseau.flux.domain.RefMatriceFlux.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
