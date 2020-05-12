package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  // Создание новой группы
  public void testGroupCreation() {
    app.goTo().GroupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("MyFirstGroup", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);

    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
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

