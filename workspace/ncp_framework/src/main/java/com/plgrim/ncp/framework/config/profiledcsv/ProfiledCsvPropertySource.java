package com.plgrim.ncp.framework.config.profiledcsv;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProfiledCsvPropertySource extends EnumerablePropertySource<ProfiledCsv> {
	private String symbol;

	protected ProfiledCsvPropertySource(ProfiledCsv csv) {
		super("ProfiledCsvPropertySource", csv);
	}

	public ProfiledCsvPropertySource(String profileName) {
		this(new ProfiledCsv(open(profileName), "UTF-8"));
		this.symbol = profileName;
	}

	private static InputStream open(String profileName) {
		try {
			return new ClassPathResource("/META-INF/config/" + profileName + ".csv").getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String[] getPropertyNames() {
		return getSource().getPropertyNames();
	}

	@Override
	public Object getProperty(String name) {
		return getSource().getProperty(name);
	}

	public void activate(String[] activeProfiles) {
		String targetProfile = ArrayUtils.isEmpty(activeProfiles) ?  "local" : activeProfiles[0];
		log.info("{} Activated profiles: {}", symbol, targetProfile);
		getSource().activate(targetProfile);
	}
}