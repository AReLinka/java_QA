package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  // Создание новой группы
  public void testGroupCreation() {
    app.goTo().GroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("MyFirstGroup");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}

    /* //Варианты вычислений максимального идентификатора
    //вар. 1 - цикл
    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);*/

  /* // вар. 2 - сравниватель
   Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    int max1 = after.stream().max(byId).get().getId();
    group.setId(max1);
    */

    /* //вар. 3 лямбда выражение - анонимная функция
     Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max1 = after.stream().max(byID).get().getId();
    group.setId(max1);
    */

    /* // самый короткий вариант
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
     */

