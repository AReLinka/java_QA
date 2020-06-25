package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Issues extends ForwardingSet<Issue> {
  private Set<Issue> delegate;

  public Issues(Issues issues) {
    this.delegate = new HashSet<Issue>(issues.delegate);
  }

  public Issues() {
    this.delegate = new HashSet<Issue>();
  }

  public Issues(Collection<Issue> issues) {
    this.delegate = new HashSet<Issue>(issues);
  }

   @Override
  protected Set<Issue> delegate() {
    return delegate;
  }

  public Issues withAdded(Issue issue) {
    Issues issues = new Issues(this);
    issues.add(issue);
    return issues;
  }

  public Issues without(Issue issue) {
    Issues issues = new Issues(this);
    issues.remove(issue);
    return issues;
  }
}