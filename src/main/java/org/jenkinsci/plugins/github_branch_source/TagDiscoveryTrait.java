package org.jenkinsci.plugins.github_branch_source;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import jenkins.scm.api.SCMHeadCategory;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.trait.SCMSourceContext;
import jenkins.scm.api.trait.SCMSourceTrait;
import jenkins.scm.api.trait.SCMSourceTraitDescriptor;
import jenkins.scm.impl.TagSCMHeadCategory;
import jenkins.scm.impl.trait.Discovery;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * A {@link Discovery} trait for GitHub that will discover tags on the repository.
 *
 * @since 2.2.3
 */
public class TagDiscoveryTrait extends SCMSourceTrait {

    /**
     * Constructor for stapler.
     */
    @DataBoundConstructor
    public TagDiscoveryTrait() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateContext(SCMSourceContext<?, ?> context) {
        GitHubSCMSourceContext ctx = (GitHubSCMSourceContext) context;
        ctx.wantTags(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean includeCategory(@NonNull SCMHeadCategory category) {
        return category instanceof TagSCMHeadCategory;
    }

    /**
     * Our descriptor.
     */
    @Extension
    @Discovery
    public static class DescriptorImpl extends SCMSourceTraitDescriptor {

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "Discover tags";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<? extends SCMSourceContext> getContextClass() {
            return GitHubSCMSourceContext.class;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<? extends SCMSource> getSourceClass() {
            return GitHubSCMSource.class;
        }

    }
}
