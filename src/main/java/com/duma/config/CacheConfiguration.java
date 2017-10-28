package com.duma.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.duma.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.duma.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.duma.domain.CompanyMessage.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.AuthorizationRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.Post.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.CommodityData.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.Warehouse.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.SupplierRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DriverMate.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DriverMessage.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DriverEmergencyContact.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.PurchaseVehicleRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.BuyVehicleRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.RentVehicleRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.RepaymentRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.PostponeRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.MaintainRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.OutDangerRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.PolicyRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.AiRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.IllegalRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.RefundRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.ContractMarginRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.OrderFrom.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.CreditReview.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DepositContract.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.AdditionalMaterials.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.MarketingPlan.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.SettleApply.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.FormalContract.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.OverdueManagement.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.ForcedSettle.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DownPayment.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.PleasePayeeAudit.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.MonthlyManagement.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.OverdueGathering.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.RentalManagement.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.RetreatDeposit.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.ClaimSettlementAudit.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.ValidateRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.GPSRefit.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.GasRefit.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.PurchaseTax.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.InsurancePurchase.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.OnBrand.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.ReceiveVehicle.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.DataCollection.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.Vehicle.class.getName(), jcacheConfiguration);
            cm.createCache(com.duma.domain.LogRecord.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
